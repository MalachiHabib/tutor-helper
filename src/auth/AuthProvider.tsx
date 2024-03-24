import {
  createContext,
  useContext,
  useState,
  ReactNode,
  FunctionComponent,
} from 'react';

interface AuthUser {
  name?: string;
  email?: string;
}

interface AuthContextType {
  user: AuthUser | null;
  setUser: (user: AuthUser | null) => void;
}

const AuthContext = createContext<AuthContextType | null>(null);

export const useAuth = (): AuthContextType => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
};

interface AuthProviderProps {
  children: ReactNode;
}

export const AuthProvider: FunctionComponent<AuthProviderProps> = ({
  children,
}) => {
  const [user, setUser] = useState<AuthUser | null>(null);

  const value = { user, setUser };

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};
