type NavItemProps = {
    icon: string;
    text: string;
    active?: boolean;
};

export function NavItem({icon, text, active = false}: NavItemProps) {
    return (
        <a
            href='#'
            className={`flex items-center rounded px-4 py-3 transition ${
                active ? 'bg-blue-600' : 'hover:bg-slate-700'
            }`}
        >
            <i className={`fas ${icon} mr-2 w-5`}></i>
            {text}
        </a>
    );
}
