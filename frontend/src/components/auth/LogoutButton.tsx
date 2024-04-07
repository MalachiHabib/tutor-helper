import { useAuth } from '../../auth/AuthProvider';
import { isUser } from '../../utils/typeGuards';

export function LogoutButton() {
  const { user, logoutUser } = useAuth();

  if (!isUser(user)) {
    return null;
  }

  return (
    <button onClick={logoutUser} className='btn'>
      Logout {user.displayName}
    </button>
  );
}
