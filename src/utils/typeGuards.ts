import { User } from 'firebase/auth';

export function isUser(user: User | null): user is User {
  return !!user && typeof user.displayName === 'string';
}
