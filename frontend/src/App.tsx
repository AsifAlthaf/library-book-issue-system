import './App.css'
import Login from './components/Login'
import AdminDashboard from './components/AdminDashboard'
import UserDashboard from './components/UserDashboard'
import { useState } from 'react'

function App() {
  const [page, setPage] = useState<'login' | 'admin' | 'user'>('login')
  const [memberId, setMemberId] = useState<number | null>(null)
  const [toast, setToast] = useState<string | null>(null)

  function show(msg: string) {
    setToast(msg)
    setTimeout(() => setToast(null), 3000)
  }

  function handleLogin(role: 'admin' | 'user', id?: number) {
    if (role === 'user' && id) {
      setMemberId(id)
      setPage('user')
    } else if (role === 'admin') {
      setPage('admin')
    }
  }

  function handleLogout() {
    setPage('login')
    setMemberId(null)
  }

  return (
    <>
      {page === 'login' && <Login onLogin={handleLogin} />}
      {page === 'admin' && <AdminDashboard onLogout={handleLogout} onToast={show} />}
      {page === 'user' && memberId && <UserDashboard memberId={memberId} onLogout={handleLogout} onToast={show} />}
      {toast && <div className="toast" role="status">{toast}</div>}
    </>
  )
}

export default App
