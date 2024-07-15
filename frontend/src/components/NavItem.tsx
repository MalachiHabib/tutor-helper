import { Link, useLocation } from 'react-router-dom';

type NavItemProps = {
    icon: string;
    text: string;
    to: string;
};

export function NavItem({ icon, text, to }: NavItemProps) {
    const location = useLocation();
    const active = location.pathname === to;

    return (
        <Link
            to={to}
            className={`flex items-center space-x-3 rounded-lg p-2 transition-colors ${
                active ? 'bg-primary text-primary-content' : 'hover:bg-base-300'
            }`}
        >
            <i
                className={`fas ${icon} w-5`}
                aria-hidden='true'
            ></i>
            <span>{text}</span>
        </Link>
    );
}
