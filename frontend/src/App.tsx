import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { Sidebar } from 'components/Sidebar';
import { TutorsDashboard } from 'components/dashboard/TutorsDashboard';

function App() {
    return (
        <Router>
            <div className='flex h-screen overflow-hidden bg-slate-100'>
                <Sidebar />
                <main className='flex flex-1 flex-col overflow-hidden'>
                    <Routes>
                        <Route
                            path='/tutors/:tutorId'
                            element={<TutorsDashboard />}
                        />
                    </Routes>
                </main>
            </div>
        </Router>
    );
}

export default App;
