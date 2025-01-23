import React, { useState } from 'react';
import { Theme } from '../types/theme';
import { useAuth } from '../context/AuthContext';

interface LoginFormProps {
  theme: Theme;
}

// Mock user data
const MOCK_USERS = [
  { email: 'john@example.com', password: 'password123', name: 'John Doe' },
  { email: 'jane@example.com', password: 'password123', name: 'Jane Smith' }
];

const LoginForm: React.FC<LoginFormProps> = ({ theme }) => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const { login } = useAuth();

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    setError('');

    const user = MOCK_USERS.find(
      u => u.email === email && u.password === password
    );

    if (user) {
      login({ email: user.email, name: user.name });
    } else {
      setError('Invalid email or password');
    }
  };

  return (
    <form className="space-y-4" onSubmit={handleSubmit}>
      {error && (
        <div className="p-3 rounded-lg text-sm" style={{ backgroundColor: '#ff000020', color: theme.text }}>
          {error}
        </div>
      )}
      <div className="space-y-2">
        <label htmlFor="email" className="text-sm" style={{ color: theme.text }}>
          Email
        </label>
        <input
          id="email"
          type="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          className="w-full px-3 py-2 rounded-md focus:outline-none focus:ring-2"
          style={{ 
            backgroundColor: theme.background,
            color: theme.text,
            borderColor: theme.primary,
          }}
          placeholder="you@example.com"
          required
        />
      </div>
      <div className="space-y-2">
        <label htmlFor="password" className="text-sm" style={{ color: theme.text }}>
          Password
        </label>
        <input
          id="password"
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          className="w-full px-3 py-2 rounded-md focus:outline-none focus:ring-2"
          style={{ 
            backgroundColor: theme.background,
            color: theme.text,
            borderColor: theme.primary,
          }}
          required
        />
      </div>
      <button
        type="submit"
        className="w-full py-2 rounded-md transition-colors mt-6"
        style={{ 
          backgroundColor: theme.accent,
          color: theme.background,
        }}
      >
        Log in
      </button>
    </form>
  );
};

export default LoginForm;
