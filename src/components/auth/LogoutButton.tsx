import { googleLogout } from '@react-oauth/google';
import { useAuth } from '../../auth/AuthProvider';

export function LogoutButton() {
  const { setUser } = useAuth();

  const handleLogout = () => {
    // Clear user from context
    setUser(null);

    // Perform Google logout
    googleLogout();
  };

  return (
    <button
      onClick={handleLogout}
      className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded"
    >
      Logout
    </button>
  );
}
