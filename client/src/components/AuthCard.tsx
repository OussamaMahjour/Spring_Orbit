import { useState } from 'react';
import LoginForm from './LoginForm';
import SignupForm from './SignupForm';
import DarkModeToggle from './DarkModeToggle';
import { Theme } from '../types/theme';

interface AuthCardProps {
  theme: Theme;
  isDark: boolean;
  onToggleTheme: () => void;
}

const AuthCard: React.FC<AuthCardProps> = ({ theme, isDark, onToggleTheme }) => {
  const [isLogin, setIsLogin] = useState(true);

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

  const currentTheme = isDark ? colors.dark : colors.light;

  return (
    <div className="min-h-screen flex items-center justify-center relative" 
         style={{ backgroundColor: theme.background }}>
      <DarkModeToggle isDark={isDark} onToggle={onToggleTheme} />
      
      <div className="w-full max-w-md">
        <div className="text-center mb-8 flex items-center justify-center gap-2">
          <img src="/faceboot.png" alt="Logo" className="h-8 w-8" />
          <h1 className="text-2xl">
            <span style={{ color: currentTheme.primary }}>Spring</span>
            <span style={{ color: currentTheme.accent }}>Orbit</span>
          </h1>
        </div>

        <div className="rounded-lg shadow-lg overflow-hidden relative" 
             style={{ backgroundColor: currentTheme.secondary }}>
          <div className="flex relative">
            <button
              className={`flex-1 py-4 text-sm font-medium transition-colors`}
              style={{ 
                color: isLogin ? currentTheme.text : currentTheme.primary 
              }}
              onClick={() => setIsLogin(true)}
            >
              Login
            </button>
            <button
              className={`flex-1 py-4 text-sm font-medium transition-colors`}
              style={{ 
                color: !isLogin ? currentTheme.text : currentTheme.primary 
              }}
              onClick={() => setIsLogin(false)}
            >
              Sign Up
            </button>
            <div
              className="absolute bottom-0 h-1 transition-all duration-300"
              style={{
                backgroundColor: currentTheme.accent,
                width: '50%',
                left: isLogin ? '0%' : '50%'
              }}
            />
          </div>
          <div className="p-8">
            {isLogin ? 
              <LoginForm theme={currentTheme} /> : 
              <SignupForm theme={currentTheme} />
            }
          </div>
        </div>
      </div>
    </div>
  );
};

export default AuthCard; 