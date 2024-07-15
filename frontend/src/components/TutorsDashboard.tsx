import { DashboardHeader } from 'components//DashboardHeader';
import { UpcomingSessions } from 'components//UpcomingSessions';
import { RecentNotes } from 'components//RecentNotes';
import { SessionInsights } from 'components//SessionInsights';
import { LearningFocus } from 'components//LearningFocus';
import { RecommendedResources } from 'components//RecommendedResources';

export function TutorsDashboard() {
    return (
        <>
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
        </>
    );
}
