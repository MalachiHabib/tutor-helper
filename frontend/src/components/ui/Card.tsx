import { ReactNode } from 'react';

type CardProps = {
    className?: string;
    children: ReactNode;
};

export function Card({ className = '', children }: CardProps) {
    return (
        <div
            className={`overflow-hidden rounded-lg bg-white shadow ${className}`}
        >
            <div className='px-4 py-5 sm:p-6'>{children}</div>
        </div>
    );
}
