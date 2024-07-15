import { CardWithIcon } from 'components/CardWithIcon';
import { Button } from 'components/ui/Button';

type NoteItemProps = {
    title: string;
    date: string;
};

function NoteItem({ title, date }: NoteItemProps) {
    return (
        <li className='border-b border-slate-200 pb-4 last:border-b-0'>
            <strong className='text-slate-800'>{title}</strong>
            <br />
            <span className='text-slate-600'>Last edited: {date}</span>
        </li>
    );
}

export function RecentNotes() {
    return (
        <CardWithIcon
            icon='fa-sticky-note'
            title='Recent Notes'
        >
            <ul className='mb-4 space-y-4'>
                <NoteItem
                    title='Python List Comprehension'
                    date='July 15, 2023'
                />
                <NoteItem
                    title='Decorators in Python'
                    date='July 10, 2023'
                />
                <NoteItem
                    title='Error Handling and Exceptions'
                    date='July 5, 2023'
                />
            </ul>
            <Button className='w-full bg-blue-600 text-white hover:bg-blue-700'>
                View All Notes
            </Button>
        </CardWithIcon>
    );
}
