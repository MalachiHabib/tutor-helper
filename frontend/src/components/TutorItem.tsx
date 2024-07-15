type TutorItemProps = {
    name: string;
    subject: string;
    active?: boolean;
};

export function TutorItem({ name, subject, active = false }: TutorItemProps) {
    return (
        <div
            className={`mb-1 cursor-pointer rounded px-4 py-2 transition ${
                active ? 'bg-blue-600' : 'hover:bg-slate-700'
            }`}
        >
            {name} ({subject})
        </div>
    );
}
