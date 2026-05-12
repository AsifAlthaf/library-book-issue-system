import { useEffect, useState } from 'react'

const API_BASE = import.meta.env.VITE_API_BASE || 'http://localhost:8080'

type Book = { bookId: number; title: string; author: string; availability: boolean }
type IssueRecord = { issueId: number; book: Book; member: any; issueDate: string; dueDate: string; returnDate: string | null; token: string; fineAmount: number; isActive: boolean }
type BookRequest = { requestId: number; book: Book; member: any; requestDate: string; requestedHours: number; status: 'PENDING' | 'APPROVED' | 'REJECTED' }

export default function AdminDashboard({ onLogout, onToast }: { onLogout: () => void; onToast?: (msg: string) => void }) {
  const [books, setBooks] = useState<Book[]>([])
  const [issued, setIssued] = useState<IssueRecord[]>([])
  const [requests, setRequests] = useState<BookRequest[]>([])
  const [loadError, setLoadError] = useState('')
  const [newTitle, setNewTitle] = useState('')
  const [newAuthor, setNewAuthor] = useState('')

  async function loadData() {
    try {
      const bRes = await fetch(`${API_BASE}/books`)
      const iRes = await fetch(`${API_BASE}/issues`)
      const rRes = await fetch(`${API_BASE}/requests/pending`)
      const bData = await bRes.json()
      const iData = await iRes.json()
      const rData = await rRes.json()
      if (bRes.ok) setBooks(bData?.data || [])
      if (iRes.ok) setIssued((iData?.data || []).filter((x: any) => x.isActive))
      if (rRes.ok) setRequests(rData?.data || [])
    } catch (e) {
      setLoadError('Failed to load data')
    }
  }

  async function addBook() {
    if (!newTitle.trim() || !newAuthor.trim()) {
      onToast?.('Please enter title and author')
      return
    }
    try {
      const res = await fetch(`${API_BASE}/books`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ title: newTitle, author: newAuthor })
      })
      const data = await res.json()
      if (res.ok) {
        onToast?.('Book added successfully')
        setNewTitle('')
        setNewAuthor('')
        loadData()
      } else {
        onToast?.(data?.message || 'Failed to add book')
      }
    } catch (e) {
      onToast?.('Network error')
    }
  }

  async function toggleBookAvailability(bookId: number) {
    try {
      const res = await fetch(`${API_BASE}/books/${bookId}/toggle-availability`, {
        method: 'PUT'
      })
      const data = await res.json()
      if (res.ok) {
        onToast?.('Book status updated')
        loadData()
      } else {
        onToast?.(data?.message || 'Failed to update book')
      }
    } catch (e) {
      onToast?.('Network error')
    }
  }

  async function approveRequest(requestId: number) {
    try {
      const res = await fetch(`${API_BASE}/requests/${requestId}/approve`, {
        method: 'PUT'
      })
      const data = await res.json()
      if (res.ok) {
        onToast?.('Request approved')
        loadData()
      } else {
        onToast?.(data?.message || 'Failed to approve request')
      }
    } catch (e) {
      onToast?.('Network error')
    }
  }

  async function rejectRequest(requestId: number) {
    try {
      const res = await fetch(`${API_BASE}/requests/${requestId}/reject`, {
        method: 'PUT'
      })
      const data = await res.json()
      if (res.ok) {
        onToast?.('Request rejected')
        loadData()
      } else {
        onToast?.(data?.message || 'Failed to reject request')
      }
    } catch (e) {
      onToast?.('Network error')
    }
  }

  useEffect(() => {
    loadData()
  }, [])

  return (
    <main className="page-shell">
      <div className="page-frame">
        <div className="page-topbar">
          <div>
            <h1 className="page-title">Admin Dashboard</h1>
            <p className="page-subtitle">Manage books, requests, and active issues</p>
          </div>
          <button onClick={onLogout}>Logout</button>
        </div>

        {loadError && <div className="error-text">{loadError}</div>}

        <section className="compact-panel">
          <h2>Add New Book</h2>
          <div className="compact-form">
            <label className="compact-field">
              <span>Title</span>
              <input type="text" value={newTitle} onChange={e => setNewTitle(e.target.value)} />
            </label>
            <label className="compact-field">
              <span>Author</span>
              <input type="text" value={newAuthor} onChange={e => setNewAuthor(e.target.value)} />
            </label>
            <div className="compact-row" style={{ gridColumn: '1 / -1' }}>
              <button onClick={addBook}>Add Book</button>
            </div>
          </div>
        </section>

        <section className="compact-panel">
          <h2>Available Books</h2>
          <table className="compact-table">
            <thead>
              <tr>
                <th>Book ID</th>
                <th>Title</th>
                <th>Author</th>
                <th>Status</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              {books.map(b => (
                <tr key={b.bookId}>
                  <td>{b.bookId}</td>
                  <td>{b.title}</td>
                  <td>{b.author}</td>
                  <td>{b.availability ? 'Available' : 'Unavailable'}</td>
                  <td className="actions-cell">
                    <button onClick={() => toggleBookAvailability(b.bookId)}>
                      {b.availability ? 'Mark Unavailable' : 'Mark Available'}
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </section>

        <section className="compact-panel">
          <h2>Issued Books (Active)</h2>
          <table className="compact-table">
            <thead>
              <tr>
                <th>Token</th>
                <th>Book</th>
                <th>Member ID</th>
                <th>Issue Date</th>
                <th>Due Date</th>
                <th>Fine</th>
              </tr>
            </thead>
            <tbody>
              {issued.map(i => (
                <tr key={i.issueId}>
                  <td>{i.token}</td>
                  <td>{i.book?.title}</td>
                  <td>{i.member?.memberId}</td>
                  <td>{i.issueDate}</td>
                  <td>{i.dueDate}</td>
                  <td>₹ {i.fineAmount?.toFixed(2)}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </section>

        <section className="compact-panel">
          <h2>Pending Book Requests</h2>
          <table className="compact-table">
            <thead>
              <tr>
                <th>Member ID</th>
                <th>Book Title</th>
                <th>Requested Hours</th>
                <th>Request Date</th>
                <th>Status</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              {requests.map(r => (
                <tr key={r.requestId}>
                  <td>{r.member?.memberId}</td>
                  <td>{r.book?.title}</td>
                  <td>{r.requestedHours}</td>
                  <td>{r.requestDate}</td>
                  <td>{r.status}</td>
                  <td className="actions-cell">
                    <div className="compact-row">
                      <button onClick={() => approveRequest(r.requestId)}>Approve</button>
                      <button onClick={() => rejectRequest(r.requestId)}>Reject</button>
                    </div>
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
