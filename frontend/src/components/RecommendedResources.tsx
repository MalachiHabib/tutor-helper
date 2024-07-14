import {CardWithIcon} from './CardWithIcon';
import {Button} from '@radix-ui/themes';

export function RecommendedResources() {
    return (
        <CardWithIcon
            icon='fa-book'
            title='Recommended Resources'
        >
            <ul className='mb-4 space-y-4 text-slate-600'>
                <li>by David Beazley</li>
                <li>by Luciano Ramalho</li>
            </ul>
            <Button className='w-full bg-blue-600 text-white hover:bg-blue-700'>
                View All Resources
            </Button>
        </CardWithIcon>
    );
}
