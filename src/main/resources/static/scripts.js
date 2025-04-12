document.addEventListener('DOMContentLoaded', function() {
  AOS.init({
    duration: 1000,
    once: true,
    easing: 'ease-in-out',
  });

  // Navbar Scroll Effect
  const navbar = document.querySelector('.navbar');
  const navToggle = document.getElementById('navToggle');
  const navLinks = document.getElementById('navLinks');

  window.addEventListener('scroll', function() {
    if (window.scrollY > 50) {
      navbar.classList.add('scrolled');
    } else {
      navbar.classList.remove('scrolled');
    }
  });

  // Mobile Menu Toggle
  navToggle.addEventListener('click', function() {
    navLinks.classList.toggle('active');
  });

  // Close mobile menu when clicking on a link
  const navItems = document.querySelectorAll('.nav-links li a');
  navItems.forEach(item => {
    item.addEventListener('click', function() {
      navLinks.classList.remove('active');
    });
  });

  // Testimonials Slider
  const testimonialSlider = document.getElementById('testimonialsSlider');
  const dots = document.querySelectorAll('.dot');
  const testimonialWidth = document.querySelector('.testimonial-card').offsetWidth + 32; // Card width + gap
  let currentIndex = 0;

  // Set initial active dot
  dots[0].classList.add('active');

  // Click event for dots
  dots.forEach((dot, index) => {
    dot.addEventListener('click', () => {
      currentIndex = index;
      updateSlider();
    });
  });

  function updateSlider() {
    // Update slider position
    testimonialSlider.scrollTo({
      left: currentIndex * testimonialWidth,
      behavior: 'smooth'
    });

    // Update active dot
    dots.forEach((dot, i) => {
      if (i === currentIndex) {
        dot.classList.add('active');
      } else {
        dot.classList.remove('active');
      }
    });
  }

  // Listen for scroll events on the slider
  testimonialSlider.addEventListener('scroll', function() {
    const scrollPos = testimonialSlider.scrollLeft;
    const newIndex = Math.round(scrollPos / testimonialWidth);

    if (newIndex !== currentIndex) {
      currentIndex = newIndex;

      // Update active dot
      dots.forEach((dot, i) => {
        if (i === currentIndex) {
          dot.classList.add('active');
        } else {
          dot.classList.remove('active');
        }
      });
    }
  });

  // Auto scroll for testimonials (optional)
  /*
  setInterval(() => {
    currentIndex = (currentIndex + 1) % dots.length;
    updateSlider();
  }, 5000);
  */

  // Counter animation for stats
  const counters = document.querySelectorAll('.counter');
  const speed = 200; // The lower the faster

  function animateCounter(counter) {
    const target = +counter.innerText.replace(/[^\d]/g, '');
    const suffix = counter.innerText.replace(/[\d]/g, '');
    let count = 0;
    const inc = target / speed;

    function updateCount() {
      if (count < target) {
        count += inc;
        counter.innerText = Math.ceil(count) + suffix;
        setTimeout(updateCount, 1);
      } else {
        counter.innerText = target + suffix;
      }
    }

    updateCount();
  }

  // Intersection Observer for counters
  const options = {
    threshold: 0.7
  };

  const observer = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
      if (entry.isIntersecting) {
        animateCounter(entry.target);
        observer.unobserve(entry.target);
      }
    });
  }, options);

  counters.forEach(counter => {
    observer.observe(counter);
  });
});