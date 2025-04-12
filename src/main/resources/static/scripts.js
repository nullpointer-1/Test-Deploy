document.addEventListener('DOMContentLoaded', function () {
  AOS.init({
    duration: 1000,
    once: true,
    easing: 'ease-in-out',
  });

  // Navbar Scroll Effect
  const navbar = document.querySelector('.navbar');
  const navToggle = document.getElementById('navToggle');
  const navLinks = document.getElementById('navLinks');

  window.addEventListener('scroll', function () {
    if (window.scrollY > 50) {
      navbar.classList.add('scrolled');
    } else {
      navbar.classList.remove('scrolled');
    }
  });

  // Mobile Menu Toggle
  if (navToggle && navLinks) {
    navToggle.addEventListener('click', function () {
      navLinks.classList.toggle('active');
    });

    // Close mobile menu when clicking on a link
    const navItems = document.querySelectorAll('.nav-links li a');
    navItems.forEach(item => {
      item.addEventListener('click', function () {
        navLinks.classList.remove('active');
      });
    });
  }


 // Contact Form Submission
 const contactForm = document.getElementById('contactForm');
 contactForm.addEventListener('submit', async function (event) {
   event.preventDefault();

   const formData = {
     name: document.getElementById('name')?.value || '',
     email: document.getElementById('email')?.value || '',
     message: document.getElementById('message')?.value || '',
     ctsEmployee: document.getElementById('ctsEmployee')?.checked || false
   };

   // Show loading screen
   Swal.fire({
     title: 'Sending...',
     text: 'Please wait while we send your message.',
     allowOutsideClick: false,
     didOpen: () => {
       Swal.showLoading();
     }
   });

   try {
     const response = await fetch('api/contact', {
       method: 'POST',
       headers: {
         'Content-Type': 'application/json'
       },
       body: JSON.stringify(formData)
     });

     if (response.ok) {
       contactForm.reset();

       // Close the loading and show success
       Swal.fire({
         icon: 'success',
         title: 'Message Sent!',
         text: 'Thank you for reaching out.',
         timer: 2000,
         showConfirmButton: false
       }).then(() => {
         const modalElement = document.getElementById('contactUsModal');
         const modal = bootstrap.Modal.getInstance(modalElement);
         modal?.hide();
       });
     } else {
       Swal.fire({
         icon: 'error',
         title: 'Oops...',
         text: 'Error sending message. Please try again.',
         confirmButtonColor: '#d33'
       });
     }
   } catch (error) {
     console.error('Error:', error);
     Swal.fire({
       icon: 'error',
       title: 'Something went wrong!',
       text: 'Please try again later.',
       confirmButtonColor: '#d33'
     });
   }
 });


  // Open Contact Us Modal
  const openModalButton = document.querySelector('#openContactUsModalButton');
  if (openModalButton) {
    openModalButton.addEventListener('click', function () {
      const modal = new bootstrap.Modal(document.getElementById('contactUsModal'));
      modal.show();
    });
  }

  // Counter Animation for Stats
  const counters = document.querySelectorAll('.counter');
  const speed = 200;

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

  const observerOptions = {
    threshold: 0.7
  };

  const observer = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
      if (entry.isIntersecting) {
        animateCounter(entry.target);
        observer.unobserve(entry.target);
      }
    });
  }, observerOptions);

  counters.forEach(counter => {
    observer.observe(counter);
  });
});
