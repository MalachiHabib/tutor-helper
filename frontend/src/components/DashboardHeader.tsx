import {Button, Card} from '@radix-ui/themes';

export function DashboardHeader() {
    return (
        <Card className='mb-5 flex items-center justify-between bg-white p-5'>
            <div>
                <h1 className='text-2xl font-bold text-slate-800'>John Doe</h1>
                <p className='text-slate-600'>
                    Python Expert | 4.9 ⭐ (50 sessions)
                </p>
            </div>
            <Button className='bg-blue-600 text-white hover:bg-blue-700'>
                Message Tutor
            </Button>
        </Card>
    );
}
