import { useState, type FormEvent } from 'react'

const API_BASE = import.meta.env.VITE_API_BASE || 'http://localhost:8080'

export default function RequestForm({ onToast }: { onToast: (msg: string) => void }) {
  const [memberId, setMemberId] = useState('')
  const [bookId, setBookId] = useState('')

  async function submit(e: FormEvent) {
    e.preventDefault()
    try {
      const res = await fetch(`${API_BASE}/requests?memberId=${encodeURIComponent(memberId)}&bookId=${encodeURIComponent(bookId)}`, { method: 'POST' })
      const data = await res.json()
      if (res.ok) {
        onToast('Request submitted')
      } else {
        onToast(data?.message || 'Request failed')
      }
    } catch (err) {
      onToast('Network error')
    }
  }

  return (
    <section aria-labelledby="request-heading" style={{marginBottom:24}}>
      <h2 id="request-heading">Member: Request Book</h2>
      <form onSubmit={submit} style={{display:'flex',flexDirection:'column',gap:8,maxWidth:360}}>
        <label>Member ID
          <input value={memberId} onChange={e=>setMemberId(e.target.value)} />
        </label>
        <label>Book ID
          <input value={bookId} onChange={e=>setBookId(e.target.value)} />
        </label>
        <button type="submit">Request Book</button>
      </form>
    </section>
  )
}
