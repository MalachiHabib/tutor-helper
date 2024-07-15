import { createRoot } from 'react-dom/client';
import App from './App';
import './index.css';
import '@radix-ui/themes/styles.css';
import React from 'react';

const root = document.getElementById('root');
if (!root) {
    throw new Error('No root element found');
}

createRoot(root).render(
    <React.StrictMode>
        <App />
    </React.StrictMode>,
);
