import {ReactNode} from 'react';
import {Card} from '@radix-ui/themes';

type CardWithIconProps = {
    icon: string;
    title: string;
    children: ReactNode;
};

export function CardWithIcon({icon, title, children}: CardWithIconProps) {
    return (
        <Card className='mb-5 bg-white'>
            <div className='p-5'>
                <h2 className='mb-4 flex items-center text-xl font-semibold text-slate-800'>
                    <i className={`fas ${icon} mr-2 text-blue-600`}></i>
                    {title}
                </h2>
                {children}
            </div>
        </Card>
    );
}
