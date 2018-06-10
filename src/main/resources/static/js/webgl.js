var camera, scene, renderer;

var targetRotation = 0;
var targetRotationOnMouseDown = 0;
var mouseX = 0;
var mouseXOnMouseDown = 0;
var windowHalfX = 600 / 2;
var windowHalfY = 400 / 2;

function drawCanvas() {
  var canvas = document.getElementById('glCanvas');

  renderer = new THREE.WebGLRenderer({
    antialias: true
  });

  renderer.setSize(600, 400);
  canvas.appendChild(renderer.domElement);

  scene = new THREE.Scene();
  scene.background = new THREE.Color(0xe0e0e0);

  camera = new THREE.PerspectiveCamera(40, window.innerWidth / window.innerHeight, 0.2, 1000);
  camera.position.z = 150;
  scene.add(camera);

  var geometry = new THREE.BoxGeometry(50, 50, 50);
  var material = new THREE.MeshBasicMaterial({
    color: 0xFF4040
  });

  var mesh = new THREE.Mesh(geometry, material);
  scene.add(mesh);
  canvas.addEventListener('mousedown', onDocumentMouseDown, false);
  canvas.addEventListener('touchstart', onDocumentTouchStart, false);
  canvas.addEventListener('touchmove', onDocumentTouchMove, false);


  renderer.render(scene, camera);

}

function onDocumentMouseDown(event) {
  event.preventDefault();
  canvas.addEventListener('mousemove', onDocumentMouseMove, false);
  canvas.addEventListener('mouseup', onDocumentMouseUp, false);
  canvas.addEventListener('mouseout', onDocumentMouseOut, false);
  mouseXOnMouseDown = event.clientX - windowHalfX;
  targetRotationOnMouseDown = targetRotation;
}

function onDocumentMouseMove(event) {
  console.log('mousemove');
  mouseX = event.clientX - windowHalfX;
  targetRotation = targetRotationOnMouseDown + (mouseX - mouseXOnMouseDown) * 0.02;
}

function onDocumentMouseUp(event) {
  console.log('mouseup');
  canvas.removeEventListener('mousemove', onDocumentMouseMove, false);
  canvas.removeEventListener('mouseup', onDocumentMouseUp, false);
  canvas.removeEventListener('mouseout', onDocumentMouseOut, false);
}

function onDocumentMouseOut(event) {
  console.log('mouseout');
  canvas.removeEventListener('mousemove', onDocumentMouseMove, false);
  canvas.removeEventListener('mouseup', onDocumentMouseUp, false);
  canvas.removeEventListener('mouseout', onDocumentMouseOut, false);
}

function onDocumentTouchStart(event) {
  console.log('touchstart');
  if (event.touches.length === 1) {
    event.preventDefault();
    mouseXOnMouseDown = event.touches[0].pageX - windowHalfX;
    targetRotationOnMouseDown = targetRotation;
  }
}

function onDocumentTouchMove(event) {
  console.log('touchmove');
  if (event.touches.length === 1) {
    event.preventDefault();
    mouseX = event.touches[0].pageX - windowHalfX;
    targetRotation = targetRotationOnMouseDown + (mouseX - mouseXOnMouseDown) * 0.05;
  }
}
