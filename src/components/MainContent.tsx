import OnlineLearning from '../assets/OnlineLearning.svg';

export function MainContent() {
  return (
    <main className="mt-8 md:mt-16 px-6 md:px-12">
      <section className="grid grid-cols-1 md:grid-cols-2 gap-4 items-center">
        <div className="space-y-6 text-center md:text-left">
          <h2 className="text-3xl md:text-5xl font-bold font-inter">
            Empowering Tutors with Technology
          </h2>
          <p className="text-gray-600 font-inter font-medium">
            Revolutionize your tutoring business with our smart management
            system.
          </p>
          <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
            Discover More
          </button>
        </div>
        <img
          src={OnlineLearning}
          alt="Online Learning"
          className="h-80 w-full md:h-auto"
        />
      </section>
    </main>
  );
}
