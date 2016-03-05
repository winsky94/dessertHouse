define("echarts/chart/heatmap", ["require", "./base", "../layer/heatmap", "../config", "../util/ecData", "zrender/tool/util", "zrender/tool/color", "zrender/shape/Image", "../chart"], function (e) {
    function t(e, t, n, a, o) {
        i.call(this, e, t, n, a, o), this.refresh(a)
    }

    var i = e("./base"), n = e("../layer/heatmap"), a = e("../config"), o = (e("../util/ecData"), e("zrender/tool/util")), r = (e("zrender/tool/color"), e("zrender/shape/Image"));
    return a.heatmap = {zlevel: 0, z: 2, clickable: !0}, t.prototype = {
        type: a.CHART_TYPE_HEATMAP,
        refresh: function (e) {
            this.clear(), e && (this.option = e, this.series = e.series), this._init()
        },
        _init: function () {
            var e = this.series;
            this.backupShapeList();
            for (var t = e.length, i = 0; t > i; ++i)if (e[i].type === a.CHART_TYPE_HEATMAP) {
                e[i] = this.reformOption(e[i]);
                var o = new n(e[i]), s = o.getCanvas(e[i].data, this.zr.getWidth(), this.zr.getHeight()), l = new r({
                    position: [0, 0],
                    scale: [1, 1],
                    hoverable: this.option.hoverable,
                    style: {x: 0, y: 0, image: s, width: s.width, height: s.height}
                });
                this.shapeList.push(l)
            }
            this.addShapeList()
        }
    }, o.inherits(t, i), e("../chart").define("heatmap", t), t
}), define("echarts/layer/heatmap", ["require"], function () {
    function e(e) {
        if (this.option = e, e)for (var i in t)this.option[i] = void 0 !== e[i] ? e[i] : t[i]; else this.option = t
    }

    var t = {
        blurSize: 30,
        gradientColors: ["blue", "cyan", "lime", "yellow", "red"],
        minAlpha: .05,
        valueScale: 1,
        opacity: 1
    }, i = 20, n = 256;
    return e.prototype = {
        getCanvas: function (e, t, a) {
            var o = this._getBrush(), r = this._getGradient(), s = i + this.option.blurSize, l = document.createElement("canvas");
            l.width = t, l.height = a;
            for (var h = l.getContext("2d"), m = e.length, V = 0; m > V; ++V) {
                var d = e[V], U = d[0], p = d[1], c = d[2], u = Math.min(1, Math.max(c * this.option.valueScale || this.option.minAlpha, this.option.minAlpha));
                h.globalAlpha = u, h.drawImage(o, U - s, p - s)
            }
            for (var g = h.getImageData(0, 0, l.width, l.height), y = g.data, m = y.length / 4; m--;) {
                var b = 4 * m + 3, u = y[b] / 256, f = Math.floor(u * (n - 1));
                y[b - 3] = r[4 * f], y[b - 2] = r[4 * f + 1], y[b - 1] = r[4 * f + 2], y[b] *= this.option.opacity
            }
            return h.putImageData(g, 0, 0), l
        }, _getBrush: function () {
            if (!this._brushCanvas) {
                this._brushCanvas = document.createElement("canvas");
                var e = i + this.option.blurSize, t = 2 * e;
                this._brushCanvas.width = t, this._brushCanvas.height = t;
                var n = this._brushCanvas.getContext("2d");
                n.shadowOffsetX = t, n.shadowBlur = this.option.blurSize, n.shadowColor = "black", n.beginPath(), n.arc(-e, e, i, 0, 2 * Math.PI, !0), n.closePath(), n.fill()
            }
            return this._brushCanvas
        }, _getGradient: function () {
            if (!this._gradientPixels) {
                var e = n, t = document.createElement("canvas");
                t.width = 1, t.height = e;
                for (var i = t.getContext("2d"), a = i.createLinearGradient(0, 0, 0, e), o = this.option.gradientColors.length, r = 0; o > r; ++r)"string" == typeof this.option.gradientColors[r] ? a.addColorStop((r + 1) / o, this.option.gradientColors[r]) : a.addColorStop(this.option.gradientColors[r].offset, this.option.gradientColors[r].color);
                i.fillStyle = a, i.fillRect(0, 0, 1, e), this._gradientPixels = i.getImageData(0, 0, 1, e).data
            }
            return this._gradientPixels
        }
    }, e
}), define("echarts/layer/heatmap", ["require"], function () {
    function e(e) {
        if (this.option = e, e)for (var i in t)this.option[i] = void 0 !== e[i] ? e[i] : t[i]; else this.option = t
    }

    var t = {
        blurSize: 30,
        gradientColors: ["blue", "cyan", "lime", "yellow", "red"],
        minAlpha: .05,
        valueScale: 1,
        opacity: 1
    }, i = 20, n = 256;
    return e.prototype = {
        getCanvas: function (e, t, a) {
            var o = this._getBrush(), r = this._getGradient(), s = i + this.option.blurSize, l = document.createElement("canvas");
            l.width = t, l.height = a;
            for (var h = l.getContext("2d"), m = e.length, V = 0; m > V; ++V) {
                var d = e[V], U = d[0], p = d[1], c = d[2], u = Math.min(1, Math.max(c * this.option.valueScale || this.option.minAlpha, this.option.minAlpha));
                h.globalAlpha = u, h.drawImage(o, U - s, p - s)
            }
            for (var g = h.getImageData(0, 0, l.width, l.height), y = g.data, m = y.length / 4; m--;) {
                var b = 4 * m + 3, u = y[b] / 256, f = Math.floor(u * (n - 1));
                y[b - 3] = r[4 * f], y[b - 2] = r[4 * f + 1], y[b - 1] = r[4 * f + 2], y[b] *= this.option.opacity
            }
            return h.putImageData(g, 0, 0), l
        }, _getBrush: function () {
            if (!this._brushCanvas) {
                this._brushCanvas = document.createElement("canvas");
                var e = i + this.option.blurSize, t = 2 * e;
                this._brushCanvas.width = t, this._brushCanvas.height = t;
                var n = this._brushCanvas.getContext("2d");
                n.shadowOffsetX = t, n.shadowBlur = this.option.blurSize, n.shadowColor = "black", n.beginPath(), n.arc(-e, e, i, 0, 2 * Math.PI, !0), n.closePath(), n.fill()
            }
            return this._brushCanvas
        }, _getGradient: function () {
            if (!this._gradientPixels) {
                var e = n, t = document.createElement("canvas");
                t.width = 1, t.height = e;
                for (var i = t.getContext("2d"), a = i.createLinearGradient(0, 0, 0, e), o = this.option.gradientColors.length, r = 0; o > r; ++r)"string" == typeof this.option.gradientColors[r] ? a.addColorStop((r + 1) / o, this.option.gradientColors[r]) : a.addColorStop(this.option.gradientColors[r].offset, this.option.gradientColors[r].color);
                i.fillStyle = a, i.fillRect(0, 0, 1, e), this._gradientPixels = i.getImageData(0, 0, 1, e).data
            }
            return this._gradientPixels
        }
    }, e
});