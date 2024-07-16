import { UpcomingSessions } from 'components//UpcomingSessions';
import { RecentNotes } from 'components//RecentNotes';
import { SessionInsights } from 'components//SessionInsights';
import { LearningFocus } from 'components//LearningFocus';
import { RecommendedResources } from 'components//RecommendedResources';
import { DashboardHeader } from 'components/dashboard/DashboardHeader';
import 'components/dashboard/styles/dashboard.css';

export function TutorsDashboard() {
    return (
        <div className='flex h-screen flex-col'>
            <DashboardHeader />
            <div className='custom-scrollbar flex-1 overflow-auto'>
                <div className='p-8'>
                    <div className='grid grid-cols-3 gap-6'>
                        <div className='col-span-2 space-y-6'>
                            <UpcomingSessions />
                            <RecentNotes />
                        </div>
                        <div className='space-y-6'>
                            <SessionInsights />
                            <LearningFocus />
                            <RecommendedResources />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
