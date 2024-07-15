import { NavItem } from './NavItem';
import { TutorItem } from './TutorItem';

export function Sidebar() {
    return (
        <aside className='flex h-screen w-64 flex-col bg-slate-800 p-5 text-white'>
            <div className='mb-8 flex items-center text-2xl font-bold'>
                <i className='fas fa-code mr-2'></i>
                CodeMentor
            </div>
            <nav className='mb-8 space-y-1'>
                <NavItem
                    icon='fa-home'
                    text='Dashboard'
                    active
                />
                <NavItem
                    icon='fa-book'
                    text='All Notes'
                />
                <NavItem
                    icon='fa-calendar-alt'
                    text='Schedule'
                />
                <NavItem
                    icon='fa-cog'
                    text='Settings'
                />
            </nav>
            <div className='mt-auto'>
                <h3 className='mb-2'>My Tutors</h3>
                <TutorItem
                    name='John Doe'
                    subject='Python'
                    active
                />
                <TutorItem
                    name='Jane Smith'
                    subject='JavaScript'
                />
                <TutorItem
                    name='Mike Johnson'
                    subject='Java'
                />
            </div>
        </aside>
    );
}
