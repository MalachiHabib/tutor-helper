import { Button } from 'components/ui/Button';
import { CardWithIcon } from 'components/CardWithIcon';

type SessionItemProps = {
    title: string;
    date: string;
    time: string;
};

function SessionItem({ title, date, time }: SessionItemProps) {
    return (
        <li className='border-b border-slate-200 pb-4 last:border-b-0'>
            <strong className='text-slate-800'>{title}</strong>
            <br />
            <span className='text-slate-600'>
                {date} | {time}
            </span>
        </li>
    );
}

export function UpcomingSessions() {
    return (
        <CardWithIcon
            icon='fa-calendar-alt'
            title='Upcoming Sessions'
        >
            <ul className='mb-4 space-y-4'>
                <SessionItem
                    title='Advanced Python Techniques'
                    date='July 20, 2023'
                    time='3:00 PM - 4:30 PM'
                />
                <SessionItem
                    title='Data Structures in Python'
                    date='July 25, 2023'
                    time='2:00 PM - 3:30 PM'
                />
            </ul>
            <Button className='w-full bg-blue-600 text-white hover:bg-blue-700'>
                Schedule New Session
            </Button>
        </CardWithIcon>
    );
}
