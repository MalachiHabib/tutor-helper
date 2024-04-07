import { Footer } from './Footer';
import { Header } from './Header';
import { MainContent } from './MainContent';

export function LandingPage() {
  return (
    <div className="flex flex-col justify-between min-h-screen">
      <div className="mx-auto max-w-7xl">
        <Header />
        <MainContent />
      </div>
      <Footer />
    </div>
  );
}
