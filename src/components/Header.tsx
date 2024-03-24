import * as Tooltip from '@radix-ui/react-tooltip';
import * as Dialog from '@radix-ui/react-dialog';
import { Cross2Icon } from '@radix-ui/react-icons';
import './test.css';
import { useAuth } from '../auth/AuthProvider';
import { LogoutButton } from './auth/LogoutButton';
import { LoginButton } from './auth/LoginButton';

export function Header() {
  const { user } = useAuth();

  return (
    <Tooltip.Provider>
      <header className="bg-white py-4 px-6 md:px-12 flex justify-between items-center">
        <h1 className="text-xl font-bold">Not sure on the name yet </h1>
        <nav>
          <ul className="flex space-x-4">
            {['Home', 'About', 'Features', 'Pricing'].map((item) => (
              <Tooltip.Root key={item}>
                <Tooltip.Trigger asChild>
                  <li className="hover:text-blue-500 cursor-pointer">{item}</li>
                </Tooltip.Trigger>
                <Tooltip.Content className="TooltipContent" sideOffset={5}>
                  {item}
                  <Tooltip.Arrow className="TooltipArrow" />
                </Tooltip.Content>
              </Tooltip.Root>
            ))}
          </ul>
        </nav>
        {user ? <LogoutButton /> : <LoginButton />}
      </header>
    </Tooltip.Provider>
  );
}

export function SignUpDialog() {
  return (
    <Dialog.Root>
      <Dialog.Trigger asChild>
        <button className="bg-purple-500 hover:bg-purple-700 text-white font-bold py-2 px-4 rounded">
          Edit profile
        </button>
      </Dialog.Trigger>
      <Dialog.Portal>
        <Dialog.Overlay className="DialogOverlay" />
        <Dialog.Content className="DialogContent">
          <Dialog.Title className="DialogTitle">Edit profile</Dialog.Title>
          <Dialog.Description className="DialogDescription">
            Make changes to your profile here. Click save when you're done.
          </Dialog.Description>
          <fieldset className="Fieldset">
            <label className="Label" htmlFor="name">
              Name
            </label>
            <input className="Input" id="name" defaultValue="Pedro Duarte" />
          </fieldset>
          <fieldset className="Fieldset">
            <label className="Label" htmlFor="username">
              Username
            </label>
            <input className="Input" id="username" defaultValue="@peduarte" />
          </fieldset>
          <div
            style={{
              display: 'flex',
              marginTop: 25,
              justifyContent: 'flex-end',
            }}
          >
            <Dialog.Close asChild>
              <button className="Button green">Save changes</button>
            </Dialog.Close>
          </div>
          <Dialog.Close asChild>
            <button className="IconButton" aria-label="Close">
              <Cross2Icon />
            </button>
          </Dialog.Close>
        </Dialog.Content>
      </Dialog.Portal>
    </Dialog.Root>
  );
}
