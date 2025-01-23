import React from 'react';
import Sidebar from './Sidebar';
import DarkModeToggle from './DarkModeToggle';

interface LayoutProps {
  children: React.ReactNode;
  theme: Theme;
  isDark: boolean;
  onToggleTheme: () => void;
}

interface Theme {
  text: string;
  background: string;
  primary: string;
  secondary: string;
  accent: string;
}

const Layout: React.FC<LayoutProps> = ({ children, theme, isDark, onToggleTheme }) => {
  return (
    <div className="min-h-screen flex" style={{ backgroundColor: theme.background }}>
      <Sidebar theme={theme} />
      <div className="flex-1">
        <header className="h-16 flex items-center justify-between px-4 border-b" style={{ borderColor: theme.secondary }}>
          <div className="flex items-center gap-2">
            <img src="/faceboot.png" alt="Logo" className="h-8 w-8" />
            <h1 className="text-xl">
              <span style={{ color: theme.primary }}>Spring</span>
              <span style={{ color: theme.accent }}>Orbit</span>
            </h1>
          </div>
          <div className="flex items-center gap-4">
            <input
              type="search"
              placeholder="Search..."
              className="px-4 py-2 rounded-full"
              style={{
                backgroundColor: theme.secondary,
                color: theme.text,
                borderColor: theme.primary,
              }}
            />
            <DarkModeToggle isDark={isDark} onToggle={onToggleTheme} />
          </div>
        </header>
        <main className="p-4">
          {children}
        </main>
      </div>
    </div>
  );
};

export default Layout; 