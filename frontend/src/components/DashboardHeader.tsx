import { Card } from 'components/ui/Card';
import { Button } from 'components/ui/Button';

export function DashboardHeader() {
    return (
        <Card className='mb-6 bg-white shadow-sm'>
            <div className='flex items-center justify-between p-6'>
                <div className='space-y-1'>
                    <h1 className='text-2xl font-bold text-gray-900'>
                        John Doe
                    </h1>
                    <p className='text-sm text-gray-600'>
                        Python Expert |{' '}
                        <span className='text-yellow-500'>4.9 ⭐</span> (50
                        sessions)
                    </p>
                </div>
                <Button
                    size='sm'
                    className='bg-blue-600 px-4 py-2 text-white transition duration-150 ease-in-out hover:bg-blue-700'
                >
                    Message Tutor
                </Button>
            </div>
        </Card>
    );
}
