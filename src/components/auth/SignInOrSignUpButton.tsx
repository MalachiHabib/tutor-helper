import * as Dialog from '@radix-ui/react-dialog';
import { SignUpModal } from './SignUpModal';

export function SignInOrSignUpButton() {
  return (
    <Dialog.Root>
      <Dialog.Trigger>
        <div className='navbar-end'></div>
        <div className='btn'>Sign Up</div>
      </Dialog.Trigger>
      <SignUpModal />
    </Dialog.Root>
  );
}
