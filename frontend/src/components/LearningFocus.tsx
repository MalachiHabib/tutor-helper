import {CardWithIcon} from './CardWithIcon';
import {Button} from '@radix-ui/themes';

export function LearningFocus() {
    return (
        <CardWithIcon
            icon='fa-lightbulb'
            title='Learning Focus'
        >
            <ul className='mb-4 space-y-4'>
                <li>
                    <strong className='text-slate-800'>Current:</strong>
                    <span className='text-slate-600'>
                        Advanced Python Concepts
                    </span>
                </li>
                <li>
                    <strong className='text-slate-800'>Next:</strong>
                    <span className='text-slate-600'>
                        Web Development with Django
                    </span>
                </li>
            </ul>
            <Button className='w-full bg-blue-600 text-white hover:bg-blue-700'>
                Update Focus
            </Button>
        </CardWithIcon>
    );
}
