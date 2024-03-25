import { User, signOut } from 'firebase/auth';
import {
  createContext,
  useContext,
  useState,
  ReactNode,
  FunctionComponent,
} from 'react';
import { auth } from '../../firebaseConfig';

interface AuthContextType {
  user: User | null;
  setUser: (user: User | null) => void;
  logoutUser: () => void;
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
  const [user, setUser] = useState<User | null>(null);

  const logoutUser = () => {
    signOut(auth)
      .then(() => {
        setUser(null);
        console.log(user);
      })
      .catch((error) => {
        console.error('Error signing out:', error);
      });
  };

  const value = { user, setUser, logoutUser };

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};
