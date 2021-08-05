function importAndSet(path, name) {
    import(path).then(module => {
        window[name] = module[name];
    });
}

document.addEventListener("DOMContentLoaded",() => {
    importAndSet("./util/locale.js", "navigation");
});

