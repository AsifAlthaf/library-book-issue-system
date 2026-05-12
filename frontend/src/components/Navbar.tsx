import React, { type JSX } from 'react';

const containerStyle: React.CSSProperties = {
  display: 'flex',
  alignItems: 'center',
  justifyContent: 'space-between',
  padding: '12px 20px',
  borderBottom: '1px solid #e5e7eb',
  background: '#ffffff',
  fontFamily: 'Segoe UI, Roboto, Helvetica, Arial, sans-serif',
};

const leftStyle: React.CSSProperties = {
  fontSize: '20px',
  fontWeight: 600,
  color: '#111827',
};

const rightStyle: React.CSSProperties = {
  display: 'flex',
  gap: '16px',
  alignItems: 'center',
};

const linkStyle: React.CSSProperties = {
  color: '#374151',
  textDecoration: 'none',
  fontSize: '14px',
};

export default function Navbar(): JSX.Element {
  return (
    <nav style={containerStyle} aria-label="Main navigation">
      <div style={leftStyle}>Library</div>
      <div style={rightStyle}>
        <a href="#author" style={linkStyle}>Author</a>
        <a href="#books" style={linkStyle}>Books</a>
        <a href="#profile" style={linkStyle}>Profile</a>
      </div>
    </nav>
  );
}
