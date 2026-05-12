import { useEffect, useState } from 'react'

const API_BASE = import.meta.env.VITE_API_BASE || 'http://localhost:8080'

type Req = any

export default function AdminRequests({ onToast }: { onToast: (msg: string) => void }) {
  const [list, setList] = useState<Req[]>([])

  async function load() {
    try {
      const res = await fetch(`${API_BASE}/requests`)
      const data = await res.json()
      if (res.ok) setList(data?.data || [])
    } catch (e) {
      onToast('Failed to load requests')
    }
  }

  useEffect(()=>{ load() }, [])

  async function act(id: number, approve: boolean) {
    try {
      const res = await fetch(`${API_BASE}/requests/${id}/${approve? 'approve':'reject'}`, { method: 'PUT' })
      const data = await res.json()
      if (res.ok) {
        onToast(approve? 'Approved' : 'Rejected')
        load()
      } else {
        onToast(data?.message || 'Action failed')
      }
    } catch (e) { onToast('Network error') }
  }

  return (
    <section aria-labelledby="admin-heading">
      <h2 id="admin-heading">Admin: Pending Requests</h2>
      <div>
        {list.length === 0 && <div>No requests</div>}
        {list.map(r=> (
          <div key={r.requestId} style={{borderBottom:'1px solid #ddd',padding:8}}>
            <div>Request #{r.requestId} — Member {r.member?.memberId} — Book {r.book?.bookId} — {r.status}</div>
            <div style={{marginTop:8}}>
              <button onClick={()=>act(r.requestId, true)}>Approve</button>
              <button onClick={()=>act(r.requestId, false)} style={{marginLeft:8}}>Reject</button>
            </div>
          </div>
        ))}
      </div>
    </section>
  )
}
