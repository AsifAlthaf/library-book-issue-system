import { useState } from 'react'

export default function Login({ onLogin }: { onLogin: (role: 'admin' | 'user', id?: number) => void }) {
  const [role, setRole] = useState<'admin' | 'user' | null>(null)
  const [userId, setUserId] = useState('')

  function handleRoleSelect(r: 'admin' | 'user') {
    setRole(r)
  }

  function handleLogin() {
    if (role === 'user' && !userId.trim()) {
      alert('Please enter User ID')
      return
    }
    if (role) {
      const id = role === 'user' ? parseInt(userId) : undefined
      onLogin(role, id)
    }
  }

  if (!role) {
    return (
      <main className="page-shell" style={{ display: 'grid', placeItems: 'center' }}>
        <section className="compact-panel" style={{ width: '100%', maxWidth: 520 }}>
          <h1 className="page-title">Library Management System</h1>
          <p className="page-subtitle">Select your role</p>
          <div className="compact-row" style={{ marginTop: 12 }}>
            <button onClick={() => handleRoleSelect('admin')}>Admin / Librarian</button>
            <button onClick={() => handleRoleSelect('user')}>User / Member</button>
          </div>
        </section>
      </main>
    )
  }

  return (
    <main className="page-shell" style={{ display: 'grid', placeItems: 'center' }}>
      <section className="compact-panel" style={{ width: '100%', maxWidth: 520 }}>
        <h1 className="page-title">Library Management System</h1>
        <h2 style={{ margin: '8px 0 10px', fontSize: 18 }}>{role === 'admin' ? 'Admin Login' : 'Member Login'}</h2>
        {role === 'user' && (
          <label className="compact-field" style={{ maxWidth: 260 }}>
            <span>User/Member ID</span>
            <input type="number" value={userId} onChange={e => setUserId(e.target.value)} />
          </label>
        )}
        <div className="compact-row" style={{ marginTop: 12 }}>
          <button onClick={handleLogin}>Login</button>
          <button onClick={() => setRole(null)}>Back</button>
        </div>
      </section>
    </main>
  )
}
