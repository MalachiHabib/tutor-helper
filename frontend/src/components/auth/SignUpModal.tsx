import React, { useState } from 'react';
import { signInWithPopup, createUserWithEmailAndPassword } from 'firebase/auth';
import { auth, provider } from '../../../firebaseConfig';
import { TailSpin } from 'react-loader-spinner';
import * as Dialog from '@radix-ui/react-dialog';
import { Cross2Icon } from '@radix-ui/react-icons';
import { useAuth } from '../../auth/AuthProvider';
import { isUser } from '../../utils/typeGuards';

export const SignUpModal = () => {
  const { setUser } = useAuth();

  const [email, setEmail] = useState<string>('');
  const [password, setPassword] = useState<string>('');
  const [loading, setLoading] = useState<boolean>(false);

  const handleSignUpWithEmail = async (
    event: React.FormEvent<HTMLFormElement>
  ) => {
    event.preventDefault();
    setLoading(true);
    try {
      const data = await createUserWithEmailAndPassword(auth, email, password);
      if (isUser(data.user)) {
        setUser(data.user);
      }
    } catch (error) {
      console.error('Error signing up:', error);
    } finally {
      setLoading(false);
    }
  };

  const handleSignUpWithGoogle = async () => {
    setLoading(true);
    try {
      const data = await signInWithPopup(auth, provider);
      if (isUser(data.user)) {
        setUser(data.user);
      }
    } catch (error) {
      console.error('Error with Google sign up:', error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <Dialog.Portal>
      <Dialog.Overlay className='fixed inset-0 bg-black bg-opacity-30' />
      <Dialog.Content className='fixed inset-0 flex items-center justify-center p-4'>
        <div className='relative bg-white rounded-lg shadow-lg p-6 w-full max-w-md'>
          <Dialog.Close asChild>
            <button className='btn btn-circle btn-sm absolute right-2 top-2'>
              <Cross2Icon />
            </button>
          </Dialog.Close>
          <h3 className='text-lg font-medium text-gray-900'>Sign Up</h3>
          <form className='space-y-4 mt-4' onSubmit={handleSignUpWithEmail}>
            <input
              type='email'
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              placeholder='Email'
              required
              className='input input-bordered w-full'
            />
            <input
              type='password'
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder='Password'
              required
              className='input input-bordered w-full'
            />
            <div className='flex items-center justify-between'>
              <button
                type='submit'
                disabled={loading}
                className='btn btn-primary'
              >
                Sign Up
              </button>
              {loading && (
                <TailSpin
                  height='20'
                  width='20'
                  color='orange'
                  ariaLabel='loading'
                />
              )}
            </div>
          </form>
          <div className='divider'></div>
          <button
            onClick={handleSignUpWithGoogle}
            disabled={loading}
            className='btn btn-warning  w-full'
          >
            Sign Up with Google
          </button>
        </div>
      </Dialog.Content>
    </Dialog.Portal>
  );
};
