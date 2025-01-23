import React from 'react';
import { Theme } from '../types/theme';

interface SidebarProps {
  theme: Theme;
}

const Sidebar: React.FC<SidebarProps> = ({ theme }) => {
  return (
    <div 
      className="w-64 p-4 flex flex-col gap-4 border-r" 
      style={{ borderColor: theme.secondary }}
    >
      <div className="flex items-center gap-2 mb-8">
        <img 
          src="https://api.dicebear.com/7.x/avataaars/svg?seed=Felix" 
          alt="Profile" 
          className="w-10 h-10 rounded-full"
        />
        <div>
          <div style={{ color: theme.text }}>John Doe</div>
          <div style={{ color: theme.primary }} className="text-sm">@johndoe</div>
        </div>
      </div>

      <button 
        className="flex items-center gap-2 p-2 rounded-lg transition-colors"
        style={{ 
          backgroundColor: theme.secondary,
          color: theme.text 
        }}
      >
        <svg className="w-5 h-5" fill="currentColor" viewBox="0 0 20 20">
          <path d="M2 4a1 1 0 011-1h14a1 1 0 110 2H3a1 1 0 01-1-1zm0 6a1 1 0 011-1h14a1 1 0 110 2H3a1 1 0 01-1-1zm1 5a1 1 0 100 2h14a1 1 0 100-2H3z" />
        </svg>
        Feed
      </button>

      <button 
        className="flex items-center gap-2 p-2 rounded-lg transition-colors"
        style={{ color: theme.text }}
      >
        <svg className="w-5 h-5" fill="currentColor" viewBox="0 0 20 20">
          <path d="M10 2a6 6 0 00-6 6v3.586l-.707.707A1 1 0 004 14h12a1 1 0 00.707-1.707L16 11.586V8a6 6 0 00-6-6zM10 18a3 3 0 01-3-3h6a3 3 0 01-3 3z" />
        </svg>
        Notifications
      </button>

      <button 
        className="flex items-center gap-2 p-2 rounded-lg transition-colors"
        style={{ color: theme.text }}
      >
        <svg className="w-5 h-5" fill="currentColor" viewBox="0 0 20 20">
          <path d="M10 9a3 3 0 100-6 3 3 0 000 6zm-7 9a7 7 0 1114 0H3z" />
        </svg>
        Profile
      </button>
    </div>
  );
};

export default Sidebar; 