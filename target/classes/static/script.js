document.addEventListener('DOMContentLoaded', function () {
    // DOM Elements
    const alertContainer = document.getElementById('alertContainer');
    const vehiclesGrid = document.getElementById('vehiclesGrid');
    const loadingIndicator = document.getElementById('loadingIndicator');
    const emptyState = document.getElementById('emptyState');
    const vehicleForm = document.getElementById('vehicleForm');
    const typeSelect = document.querySelector('select[name="type"]');
    const suvFields = document.getElementById('suvFields');
    const sedanFields = document.getElementById('sedanFields');
    const hatchbackFields = document.getElementById('hatchbackFields');
    const coupeFields = document.getElementById('coupeFields');
    const filterButtons = document.querySelectorAll('.filter-btn');

    // API Configuration
    const API_BASE = '/api/cars';
    let currentFilter = 'all';

    // Initialize
    setupEventListeners();
    loadVehicles(currentFilter);




    // Event Listeners Setup
    function setupEventListeners() {
        // Vehicle Type Toggle
        typeSelect.addEventListener('change', function () {
            const type = this.value;
            suvFields.classList.toggle('d-none', type !== 'SUV');
            sedanFields.classList.toggle('d-none', type !== 'Sedan');
            hatchbackFields.classList.toggle('d-none', type !== 'Hatchback');
            coupeFields.classList.toggle('d-none', type !== 'Coupe');
        });

        // Filter Buttons
        filterButtons.forEach(button => {
            button.addEventListener('click', function () {
                filterButtons.forEach(btn => btn.classList.remove('active'));
                this.classList.add('active');
                currentFilter = this.dataset.filter;
                loadVehicles(currentFilter);
            });
        });

        // Form Submission
        vehicleForm.addEventListener('submit', async function (e) {
            e.preventDefault();
            const formData = new FormData(this);
            const data = Object.fromEntries(formData.entries());

            try {
                showLoading(true);
                const response = await fetch(`${API_BASE}/add`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                    },
                    body: new URLSearchParams(data)
                });

                if (!response.ok) throw new Error('Failed to add vehicle');

                showAlert('Vehicle added successfully!', 'success');
                $('#addCarModal').modal('hide');
                this.reset();
                loadVehicles(currentFilter);
            } catch (error) {
                showAlert(error.message, 'danger');
            } finally {
                showLoading(false);
            }
        });
    }

    // Load Vehicles
    async function loadVehicles(filter = 'all') {
        try {
            showLoading(true);
            clearGrid();

            const endpoint = filter === 'all' ? API_BASE : `${API_BASE}/${filter}`;
            const response = await fetch(endpoint);

            if (!response.ok) throw new Error('Failed to load vehicles');

            const vehicles = await response.json();
            renderVehicles(vehicles);

            emptyState.classList.toggle('d-none', vehicles.length > 0);
        } catch (error) {
            showAlert(error.message, 'danger');
        } finally {
            showLoading(false);
        }
    }

    // Render Vehicles
    function renderVehicles(vehicles) {
        vehiclesGrid.innerHTML = vehicles.map(vehicle => {
            const vehicleType = getVehicleType(vehicle);
            const badgeColor = getBadgeColor(vehicleType);
            const features = getVehicleFeatures(vehicle, vehicleType);

            // SAFE PRICE HANDLING - UPDATED
            const salePrice = vehicle.salePrice !== undefined ?
                vehicle.salePrice.toFixed(2) :
                vehicle.regularPrice.toFixed(2);

            const regularPrice = vehicle.regularPrice !== undefined ?
                vehicle.regularPrice.toFixed(2) :
                '0.00';

            return `
                <div class="col">
                    <div class="vehicle-card card h-100">
                        <span class="type-badge ${badgeColor}">${vehicleType}</span>
                        <img src="${getVehicleImage(vehicle, vehicleType)}" class="card-img-top" alt="${vehicle.model}">
                        <div class="card-body">
                            <h5 class="card-title">${vehicle.model}</h5>
                            <p class="card-text text-muted">
                                <i class="fas fa-palette me-1"></i>${vehicle.color}
                                <i class="fas fa-tachometer-alt ms-3 me-1"></i>${vehicle.speed} km/h
                            </p>
                            <div class="d-flex justify-content-between align-items-center mt-3">
                                <div>
                                    <span class="price-tag">₹${salePrice}</span>
                                    <span class="original-price ms-2">₹${regularPrice}</span>
                                </div>
                                <div>${features}</div>
                            </div>
                        </div>
                    </div>
                </div>
            `;
        }).join('');
    }


    // Helper Functions
    function showLoading(show) {
        loadingIndicator.style.display = show ? 'flex' : 'none';
        vehiclesGrid.style.display = show ? 'none' : 'grid';
    }

    function clearGrid() {
        vehiclesGrid.innerHTML = '';
    }

    function showAlert(message, type) {
        const alert = document.createElement('div');
        alert.className = `alert alert-${type} alert-dismissible fade show mb-4`;
        alert.innerHTML = `
            ${message}
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        `;
        alertContainer.innerHTML = '';
        alertContainer.prepend(alert);

        setTimeout(() => {
            alert.classList.remove('show');
            setTimeout(() => alert.remove(), 150);
        }, 5000);
    }

    function getVehicleType(vehicle) {
        if (vehicle.weight !== undefined) return 'SUV';
        if (vehicle.year !== undefined) return 'Sedan';
        if (vehicle.bootspace !== undefined) return 'Hatchback';
        if (vehicle.seatCount !== undefined) return 'Coupe';
        return 'Vehicle';
    }

    function getBadgeColor(vehicleType) {
        const colors = {
            'SUV': 'bg-primary',
            'Sedan': 'bg-success',
            'Hatchback': 'bg-warning',
            'Coupe': 'bg-danger'
        };
        return colors[vehicleType] || 'bg-secondary';
    }

    function getVehicleFeatures(vehicle, type) {
        switch (type) {
            case 'SUV':
                return `<span class="badge bg-secondary me-1">${vehicle.weight}kg</span>`;
            case 'Sedan':
                return `<span class="badge bg-secondary me-1">${vehicle.year || 'N/A'}</span>`;
            case 'Hatchback':
                return `<span class="badge bg-secondary me-1">${vehicle.bootspace}L</span>`;
            case 'Coupe':
                return `<span class="badge bg-secondary me-1">${vehicle.seatCount} seats</span>`;
            default:
                return '';
        }
    }
    function getVehicleImage(vehicle, type) {
        const images = [
            'sedanimg1.jfif', 'sedanimg2.jfif',
            'hatchback1.jfif',
            'coupeimg1.jfif', 'coupeimg2.jfif', 'coupeimg3.jfif',
            'suvimg1.jfif', 'suvimg2.jfif', 'suvimg3.jfif'
        ];
       
        // Filter images based on the type
        const filteredImages = images.filter(image => image.toLowerCase().includes(type.toLowerCase()));
    
        if (filteredImages.length === 0) {
            console.error('No images found for the specified type');
            return '';
        }
    
        const randomImage = filteredImages[Math.floor(Math.random() * filteredImages.length)];
        return `./${randomImage}`;
    }
});
