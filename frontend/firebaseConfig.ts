import { initializeApp } from 'firebase/app';
import { getAuth, GoogleAuthProvider } from 'firebase/auth';

const firebaseConfig = {
  apiKey: 'AIzaSyDSuz9v7zqdq-EzhBP6ZwOr0INBEN1CXeA',
  authDomain: 'tutoring-application-418103.firebaseapp.com',
  projectId: 'tutoring-application-418103',
  storageBucket: 'tutoring-application-418103.appspot.com',
  messagingSenderId: '550682242062',
  appId: '1:550682242062:web:bf49ca67f37d7de455205f',
  measurementId: 'G-TXW0T713EZ',
};

// Initialise Firebase
const app = initializeApp(firebaseConfig);
const auth = getAuth(app);
const provider = new GoogleAuthProvider();
export { auth, provider };
