import type { Config } from 'tailwindcss';
import daisyui from 'daisyui';

export default {
  content: ['./src/**/*.{js,jsx,ts,tsx}'],
  theme: {
    extend: {
      fontFamily: {
        inter: ['Inter', 'sans-serif'],
      },
      fontWeight: {
        medium: '500',
        bold: '700',
      },
    },
  },
  daisyui: {
    themes: ['light', 'dark'],
  },
  plugins: [daisyui],
} satisfies Config;
