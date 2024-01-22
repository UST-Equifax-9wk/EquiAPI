/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{html,js}"],
  theme: {
    extend: {
      colors: {
        main: "#FFFFFF",
        second: "#333E48",
        third: "#007298",
        highlight: "#981E32",
        grey: "#515B63",
      },
      fontFamily: {
        main: ['"Open Sans"', "sans-serif"],
      },
    },
  },
  plugins: [],
};
