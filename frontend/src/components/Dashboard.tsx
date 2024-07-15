import { Sidebar } from './Sidebar';
import { DashboardHeader } from './DashboardHeader';
import { UpcomingSessions } from './UpcomingSessions';
import { RecentNotes } from './RecentNotes';
import { SessionInsights } from './SessionInsights';
import { LearningFocus } from './LearningFocus';
import { RecommendedResources } from './RecommendedResources';

export function Dashboard() {
    return (
        <div className='flex h-screen overflow-hidden bg-slate-100'>
            <Sidebar />
            <main className='flex flex-1 flex-col overflow-hidden'>
                <DashboardHeader />
                <div className='flex-1 overflow-hidden p-8'>
                    <div className='grid h-full grid-cols-3 gap-6'>
                        <div className='col-span-2 flex flex-col overflow-hidden'>
                            <UpcomingSessions />
                            <RecentNotes />
                        </div>
                        <div className='flex flex-col overflow-hidden'>
                            <SessionInsights />
                            <LearningFocus />
                            <RecommendedResources />
                        </div>
                    </div>
                </div>
            </main>
        </div>
    );
}
