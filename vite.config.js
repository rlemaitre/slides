import { defineConfig } from "vite";
import scalaJSPlugin from "@scala-js/vite-plugin-scalajs";
import importSideEffectPlugin from "@raquo/vite-plugin-import-side-effect";

export default defineConfig({
  plugins: [
      scalaJSPlugin(),
      importSideEffectPlugin({
        defNames: ["swiperStyles"], // see "Compact syntax" below
        rewriteModuleIds: ['**/*.css', '**/*.less'],
        verbose: true
      })
  ],
});
