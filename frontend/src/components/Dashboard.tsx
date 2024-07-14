import {Sidebar} from './Sidebar';
import {DashboardHeader} from './DashboardHeader';
import {UpcomingSessions} from './UpcomingSessions';
import {RecentNotes} from './RecentNotes';
import {SessionInsights} from './SessionInsights';
import {LearningFocus} from './LearningFocus';
import {RecommendedResources} from './RecommendedResources';

export function Dashboard() {
    return (
        <div className='flex min-h-screen bg-slate-100'>
            <Sidebar />
            <main className='flex-1 p-8'>
                <DashboardHeader />
                <div className='grid grid-cols-3 gap-6'>
                    <div className='col-span-2'>
                        <UpcomingSessions />
                        <RecentNotes />
                    </div>
                    <div>
                        <SessionInsights />
                        <LearningFocus />
                        <RecommendedResources />
                    </div>
                </div>
            </main>
        </div>
    );
}
