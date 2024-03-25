import { useAuth } from '../auth/AuthProvider';
import { LogoutButton } from './auth/LogoutButton';
import { SignInOrSignUpButton } from './auth/SignInOrSignUpButton';

export function Header() {
  const { user } = useAuth();

  return (
    <nav className='navbar bg-base-100 mt-2'>
      <div className='flex-1'>
        <h1 className='text-xl font-bold'>Not sure on the name yet </h1>
      </div>
      <div className='flex-none'>
        <ul className='flex space-x-4 items-center'>
          <li className='hover:text-blue-500 cursor-pointer'>Something</li>
          <li> {user ? <LogoutButton /> : <SignInOrSignUpButton />}</li>
        </ul>
      </div>
    </nav>
  );
}
