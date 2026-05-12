import { useEffect, useState } from 'react'

const API_BASE = import.meta.env.VITE_API_BASE || 'http://localhost:8080'

type Book = { bookId: number; title: string; author: string; availability: boolean }
type IssueRecord = { issueId: number; book: Book; issueDate: string; dueDate: string; returnDate: string | null; token: string; isActive: boolean }

export default function UserDashboard({ memberId, onLogout, onToast }: { memberId: number; onLogout: () => void; onToast: (msg: string) => void }) {
  const [books, setBooks] = useState<Book[]>([])
  const [history, setHistory] = useState<IssueRecord[]>([])
  const [loadError, setLoadError] = useState('')
  const [requestedHours, setRequestedHours] = useState('24')

  async function loadData() {
    try {
      const bRes = await fetch(`${API_BASE}/books/available`)
      const hRes = await fetch(`${API_BASE}/members/${memberId}/issues`)
      const bData = await bRes.json()
      const hData = await hRes.json()
      if (bRes.ok) setBooks(bData?.data || [])
      if (hRes.ok) setHistory(hData?.data || [])
    } catch (e) {
      setLoadError('Failed to load data')
    }
  }

  useEffect(() => {
    loadData()
  }, [memberId])

  async function requestBook(bookId: number) {
    const hours = Number(requestedHours)
    if (!Number.isFinite(hours) || hours < 1 || hours > 336) {
      onToast('Request duration must be between 1 and 336 hours')
      return
    }
    try {
      const res = await fetch(`${API_BASE}/requests?memberId=${memberId}&bookId=${bookId}&requestedHours=${hours}`, { method: 'POST' })
      const data = await res.json()
      if (res.ok) {
        onToast('Request submitted')
      } else {
        onToast(data?.message || 'Failed to request book')
      }
    } catch (e) {
      onToast('Network error')
    }
  }

  async function returnBook(issueId: number) {
    try {
      const res = await fetch(`${API_BASE}/issues/return/${issueId}?damaged=false`, {
        method: 'PUT'
      })
      const data = await res.json()
      if (res.ok) {
        onToast('Book returned successfully')
        loadData()
      } else {
        onToast(data?.message || 'Failed to return book')
      }
    } catch (e) {
      onToast('Network error')
    }
  }

  return (
    <main className="page-shell">
      <div className="page-frame">
        <div className="page-topbar">
          <div>
            <h1 className="page-title">Member Dashboard (ID: {memberId})</h1>
            <p className="page-subtitle">Request books and manage your active loans</p>
          </div>
          <button onClick={onLogout}>Logout</button>
        </div>

        {loadError && <div className="error-text">{loadError}</div>}

        <section className="compact-panel">
          <h2>Available Books</h2>
          <div className="compact-row" style={{ marginBottom: 10 }}>
            <label className="compact-field" style={{ maxWidth: 220 }}>
              <span>Request for hours</span>
              <input type="number" min="1" max="336" value={requestedHours} onChange={e => setRequestedHours(e.target.value)} />
            </label>
            <p className="muted-note">Minimum 1 hour, maximum 336 hours.</p>
          </div>
          <table className="compact-table">
            <thead>
              <tr>
                <th>Book ID</th>
                <th>Title</th>
                <th>Author</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              {books.map(b => (
                <tr key={b.bookId}>
                  <td>{b.bookId}</td>
                  <td>{b.title}</td>
                  <td>{b.author}</td>
                  <td className="actions-cell">
                    <button onClick={() => requestBook(b.bookId)}>Request</button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </section>

        <section className="compact-panel">
          <h2>Issued Books History</h2>
          <table className="compact-table">
            <thead>
              <tr>
                <th>Token</th>
                <th>Book</th>
                <th>Issue Date</th>
                <th>Due Date</th>
                <th>Returned Date</th>
                <th>Status</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              {history.map(h => (
                <tr key={h.issueId}>
                  <td>{h.token}</td>
                  <td>{h.book?.title}</td>
                  <td>{h.issueDate}</td>
                  <td>{h.dueDate}</td>
                  <td>{h.returnDate || '-'}</td>
                  <td>{h.isActive ? 'Active' : 'Returned'}</td>
                  <td className="actions-cell">
                    {h.isActive ? (
                      <button onClick={() => returnBook(h.issueId)}>Return</button>
                    ) : (
                      '-'
                    )}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </section>
      </div>
    </main>
  )
}
