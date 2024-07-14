import {CardWithIcon} from './CardWithIcon';

type InsightItemProps = {
    value: string;
    label: string;
};

function InsightItem({value, label}: InsightItemProps) {
    return (
        <div className='text-center'>
            <div className='text-2xl font-bold text-blue-600'>{value}</div>
            <div className='text-sm text-slate-600'>{label}</div>
        </div>
    );
}

export function SessionInsights() {
    return (
        <CardWithIcon
            icon='fa-chart-line'
            title='Session Insights'
        >
            <div className='flex justify-between'>
                <InsightItem
                    value='12'
                    label='Total Sessions'
                />
                <InsightItem
                    value='18h'
                    label='Total Hours'
                />
                <InsightItem
                    value='4.9'
                    label='Avg. Rating'
                />
            </div>
        </CardWithIcon>
    );
}
