import { LandingPage } from './components/LandingPage';
import {useEffect} from 'react';
import { themeChange } from 'theme-change';

export default function App() {
  useEffect(() => {
    themeChange(false);
  }, []);

  return <LandingPage />;
}
