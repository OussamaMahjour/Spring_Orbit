import React, { useState } from 'react';
import Layout from './components/Layout';
import Feed from './components/Feed';
import AuthCard from './components/AuthCard';
import { AuthProvider, useAuth } from './context/AuthContext';

const colors = {
  dark: {
    text: '#eff4eb',
    background: '#0d1109',
    primary: '#b5cea1',
    secondary: '#37684c',
    accent: '#76b7a3',
  },
  light: {
    text: '#0d1109',
    background: '#eff4eb',
    primary: '#37684c',
    secondary: '#b5cea1',
    accent: '#76b7a3',
  }
};

const AppContent = () => {
  const [isDark, setIsDark] = useState(true);
  const { user } = useAuth();
  const currentTheme = isDark ? colors.dark : colors.light;

  if (!user) {
    return <AuthCard theme={currentTheme} isDark={isDark} onToggleTheme={() => setIsDark(!isDark)} />;
  }

  return (
    <Layout theme={currentTheme} isDark={isDark} onToggleTheme={() => setIsDark(!isDark)}>
      <Feed theme={currentTheme} />
    </Layout>
  );
};

function App() {
  return (
    <AuthProvider>
      <AppContent />
    </AuthProvider>
  );
}

export default App;
