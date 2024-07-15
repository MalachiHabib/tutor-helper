import { useState } from 'react';
import { Link } from 'react-router-dom';
import { NavItem } from './NavItem';
import { TutorItem } from './TutorItem';

const allTutors = [
    { id: 1, name: 'John Doe', subject: 'Python' },
    { id: 2, name: 'Jane Smith', subject: 'JavaScript' },
    { id: 3, name: 'Mike Johnson', subject: 'Java' },
    { id: 4, name: 'Alice Brown', subject: 'Ruby' },
    { id: 5, name: 'Bob White', subject: 'Go' },
    { id: 6, name: 'Carol Green', subject: 'C#' },
];

const navItems = [
    { icon: 'fa-home', text: 'Dashboard', to: '/' },
    { icon: 'fa-book', text: 'All Notes', to: '/notes' },
    { icon: 'fa-calendar-alt', text: 'Schedule', to: '/schedule' },
    { icon: 'fa-cog', text: 'Settings', to: '/settings' },
];

export function Sidebar() {
    const [showAllTutors, setShowAllTutors] = useState(false);
    const displayedTutors = showAllTutors ? allTutors : allTutors.slice(0, 3);

    return (
        <aside className='flex h-full w-64 flex-col bg-base-200 text-base-content'>
            <div className='flex items-center space-x-2 border-b border-base-300 p-4'>
                <i className='fas fa-code text-2xl text-primary'></i>
                <span className='text-2xl font-bold'>CodeMentor</span>
            </div>
            <nav className='flex-grow space-y-2 overflow-y-auto p-4'>
                {navItems.map((item) => (
                    <NavItem
                        key={item.to}
                        {...item}
                    />
                ))}
            </nav>
            <div className='border-t border-base-300 p-4'>
                <h3 className='mb-2 flex items-center justify-between text-lg font-semibold'>
                    My Tutors
                    <span className='text-sm text-primary'>
                        {allTutors.length} total
                    </span>
                </h3>
                <div className='h-52 space-y-2 overflow-y-auto'>
                    {displayedTutors.map((tutor) => (
                        <TutorItem
                            key={tutor.id}
                            {...tutor}
                        />
                    ))}
                </div>
                {allTutors.length > 3 && (
                    <button
                        className='btn btn-outline btn-primary btn-sm mt-2 w-full'
                        onClick={() => setShowAllTutors(!showAllTutors)}
                    >
                        {showAllTutors ? 'Show Less' : 'View All Tutors'}
                    </button>
                )}
                <Link
                    to='/tutors'
                    className='btn btn-ghost btn-sm mt-2 w-full'
                >
                    Manage Tutors
                </Link>
            </div>
        </aside>
    );
}
