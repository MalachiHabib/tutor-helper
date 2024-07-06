module.exports = {
  root: true,
  env: { browser: true, es2020: true },
  extends: [
    "prettier",
    "eslint:recommended",
    "plugin:@typescript-eslint/recommended",
    "plugin:react-hooks/recommended",
    "plugin:react/recommended"
  ],
  ignorePatterns: ["dist", ".eslintrc.cjs"],
  parser: "@typescript-eslint/parser",
  plugins: ["react-refresh"],
  rules: {
    "react/jsx-uses-react": "off",
    "react/react-in-jsx-scope": "off",
    "react-refresh/only-export-components": [
      "warn",
      { allowConstantExport: true },
    ],
    indent: ["error", 2], // Enforce 2-space indentation
    quotes: ["error", "single"], // Enforce single quotes
    semi: ["error", "always"], // Enforce semicolons at the end of statements
    "no-unused-vars": "warn", // Warn about variables that are declared but not used
    eqeqeq: ["error", "always"], // Require the use of === and !==
    curly: "error", // Require curly braces for all control statements
    "@typescript-eslint/no-explicit-any": "warn", // Warn against using the any type
    "@typescript-eslint/explicit-function-return-type": "off", // Turn off requirement for explicit return types
    "@typescript-eslint/explicit-module-boundary-types": "off", // Turn off requirement for explicit return types on module boundaries
    "no-console": ["warn", { allow: ["warn", "error"] }], // Warn on console.log; allow console.warn and console.error
    "react-hooks/rules-of-hooks": "error", // Checks rules of Hooks
    "react-hooks/exhaustive-deps": "warn", // Checks effect dependencies
  },
};
