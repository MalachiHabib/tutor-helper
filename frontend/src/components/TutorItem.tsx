import { NavLink } from 'react-router-dom';

type TutorItemProps = {
    id: number;
    name: string;
    subject: string;
};

export function TutorItem({ id, name, subject }: TutorItemProps) {
    return (
        <NavLink
            to={`/tutors/${id}`}
            className={({ isActive }) =>
                `flex items-center space-x-3 rounded-lg p-2 transition-colors ${
                    isActive
                        ? 'bg-primary text-primary-content'
                        : 'hover:bg-base-300'
                }`
            }
        >
            <div className='avatar placeholder'>
                <div className='w-8 rounded-full bg-neutral text-neutral-content'>
                    <span className='text-xs'>
                        {name
                            .split(' ')
                            .map((n) => n[0])
                            .join('')}
                    </span>
                </div>
            </div>
            <div>
                <div className='font-medium'>{name}</div>
                <div className='text-sm opacity-75'>{subject}</div>
            </div>
        </NavLink>
    );
}
