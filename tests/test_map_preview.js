/* Regression checks for the original map diagnostic page. Run with Node. */
const fs = require('node:fs');
const vm = require('node:vm');
const assert = require('node:assert/strict');
const html = fs.readFileSync('analysis/original-map-preview.html', 'utf8');
const code = html.match(/<script>([\s\S]*?)<\/script>/)[1];
const calls = [];
const nodes = {};
for (const id of ['sel', 'zoom', 'btn', 'info', 'err', 'cv']) {
  nodes[id] = { value: id === 'zoom' ? '1' : '', style: {},
    appendChild() {}, addEventListener() {} };
}
nodes.cv.getContext = () => ({ clearRect() {}, drawImage(...args) { calls.push(args); } });
const map = { columns: 3, rows: 2, cells: [[0, 1, 120], [2, 0, 3]] };
class Atlas {
  constructor() { this.naturalWidth = 24; this.naturalHeight = 2880; }
  set src(path) { assert.equal(path, 'NinjaSchool1/resources/t/t.png'); this.onload(); }
}
vm.runInNewContext(code, {
  document: { getElementById: id => nodes[id], createElement: () => ({}) },
  Image: Atlas,
  fetch: async path => { assert.equal(path, 'maps/ninja1/map-000.json'); return { ok: true, json: async () => map }; }
});
setImmediate(() => {
  assert.equal(calls.length, 4, 'Draw nonempty cells using 2D rows');
  assert.deepEqual(calls.map(c => c.slice(2)), [
    [0, 24, 24, 24, 0, 24, 24],
    [2856, 24, 24, 48, 0, 24, 24],
    [24, 24, 24, 0, 24, 24, 24],
    [48, 24, 24, 48, 24, 24, 24]
  ]);
  assert.equal(nodes.cv.width, 72);
  assert.equal(nodes.cv.height, 48);
  assert.match(nodes.info.textContent, /Tile ngoài phạm vi: 0\/6/);
  console.log('MAP_PREVIEW_PASS: rows, atlas crops, coordinates, empty tiles, dimensions');
});

function harness(fetcher) {
  const elements = {};
  const draws = [];
  for (const id of ['sel', 'zoom', 'btn', 'info', 'err', 'cv']) {
    elements[id] = { value: id === 'zoom' ? '1' : '', style: {},
      handlers: {}, appendChild() {},
      addEventListener(event, callback) { this.handlers[event] = callback; } };
  }
  elements.cv.getContext = () => ({ clearRect() { draws.length = 0; }, drawImage(...args) { draws.push(args); } });
  vm.runInNewContext(code, {
    document: { getElementById: id => elements[id], createElement: () => ({}) },
    Image: Atlas, fetch: fetcher
  });
  return { elements, draws };
}
const flush = () => new Promise(resolve => setImmediate(resolve));
(async () => {
  const bad = harness(async () => ({ ok: true, json: async () => ({ columns: -1, rows: 2, cells: [] }) }));
  await flush();
  assert.equal(bad.elements.err.style.display, 'block', 'Invalid grid shows an error');
  const wide = harness(async () => ({ ok: true, json: async () => ({ columns: 400, rows: 1, cells: [Array(400).fill(1)] }) }));
  await flush();
  assert.equal(wide.elements.err.style.display, 'block', 'Oversized canvas shows an error');
  let first, second;
  const race = harness(path => new Promise(resolve => {
    if (path.includes('000')) first = resolve; else second = resolve;
  }));
  await flush();
  race.elements.sel.value = '1';
  race.elements.sel.handlers.change();
  await flush();
  second({ ok: true, json: async () => ({ columns: 1, rows: 1, cells: [[2]] }) });
  await flush();
  first({ ok: true, json: async () => map });
  await flush();
  assert.equal(race.elements.cv.width, 24, 'Old response cannot replace latest map');
  assert.match(race.elements.info.textContent, /ID 001/);
  console.log('MAP_PREVIEW_PASS: malformed data and stale response protection');
})().catch(error => { console.error(error); process.exitCode = 1; });

(async () => {
  const names = fs.readdirSync('analysis/maps/ninja1').filter(n => /^map-\d{3}\.json$/.test(n));
  assert.equal(names.length, 24);
  for (const name of names) {
    const fixture = JSON.parse(fs.readFileSync('analysis/maps/ninja1/' + name));
    const world = harness(async () => ({ ok: true, json: async () => fixture }));
    await flush();
    assert.equal(world.elements.err.style.display, 'none', name);
    assert.equal(world.elements.cv.width, fixture.columns * 24, name);
    assert.equal(world.elements.cv.height, fixture.rows * 24, name);
    const expected = fixture.cells.flat().filter(id => Number.isInteger(id) && id > 0 && id <= 120).length;
    assert.equal(world.draws.length, expected, name);
  }
  console.log('MAP_PREVIEW_PASS: all 24 original grid fixtures render within canvas limits');
})().catch(error => { console.error(error); process.exitCode = 1; });
